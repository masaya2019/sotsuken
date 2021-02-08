<?php
// デフォルトステータスをdefaultに設定
$status = "default";

// query_sql.phpを呼び出す
require('not_api/query_sql.php');

// connect_db.phpを呼び出す（データベースに接続）
// require('not_api/connect_db.php');
require('not_api/connect_database.php');

// 仮検索データ
// $search_data = "ほう";
// $search_data = "とまと";

// search_dataを受け取ったら
if (isset($_POST['search_data'])) {
    // search_dataを受け取る
    $search_data = $_POST['search_data'];

    // 検索語句を含むレコードが存在するか
    $sql = "SELECT COUNT(*) AS cnt FROM search_index WHERE keyword LIKE '%" . $search_data . "%'";

    // 接続したDBに対してSQL文を実行する
    $result = querySql($db, $sql);

    // 検索インデックスに一致する結果があるか
    $row = mysqli_fetch_array($result);

    // print "該当レコード数" . $row["cnt"] . "<br>";

    // レコードナシなら
    if ($row["cnt"] == 0) {
        // 返却値保存配列
        $response_array = array();
        // 配列の中に入れる
        array_push(
            $response_array,
            array(
                    "foodImageUrl" => "no_recode_error",
                    "recipeUrl" => "no_recode_error",
                    "recipeTitle" => "no_recode_error"
                )
        );
    // print_r($response_array);

    // レコードがあれば
    } else {
        // 該当するcategory_typeを返す
        $sql = "SELECT DISTINCT goods.category_type "
    . "FROM search_index INNER JOIN goods ON search_index.goods_id = goods.goods_id "
    . "WHERE keyword LIKE '%" . $search_data . "%'";

        // print $sql . "<br>";

        // 接続したDBに対してSQL文を実行する
        $result = querySql($db, $sql);

        // goods_id保存用配列
        $array_category_type = array();

        // 該当するcategory_typeを配列に保存
        foreach ($result as $row) {
            $category_type = $row["category_type"];
            // print $category_type . "<br>";
            // 該当するgoods_idを配列に保存

            array_push($array_category_type, $category_type);
        }

        // print_r($array_category_type);
        // print "<br>";

        // connect_db.phpを呼び出す（データベースに接続）
        require('not_api/application_id.php');

        // print_r($array_category_type);

        // 返却値保存配列
        $response_array = array();

        // カウント
        $cnt = 0;

        // 配列から該当するカテゴリータイプを取得
        foreach ($array_category_type as $category_type) {
            // 12件以上表示しないようにする（３つのカテゴリーで最大）
            if ($cnt > 5) {
                break;
            }

            // 仮カテゴリー
            // $category_type = "12-454";
            $url = "https://app.rakuten.co.jp/services/api/Recipe/CategoryRanking/20170426?format=json&categoryId="
          . $category_type . "&elements=foodImageUrl%2CrecipeTitle%2CrecipeUrl&applicationId="
          . $applicationId;

            // テスト用URL
            // $url = "http://r02isc2t119.sub.jp/api/test.json";

            // print $url . "<br>";

            $json = file_get_contents($url);
            $arr = json_decode($json, true);

            // print_r($arr["result"]);

            foreach ($arr["result"] as $data) {
                // print_r($data);
                // print "<br>";

                $foodImageUrl = $data["foodImageUrl"];
                $recipeUrl = $data["recipeUrl"];
                $recipeTitle = $data["recipeTitle"];

                // 配列の中に入れる
                array_push(
                    $response_array,
                    array(
                    "foodImageUrl" => $foodImageUrl,
                    "recipeUrl" => $recipeUrl,
                    "recipeTitle" => $recipeTitle
                )
                );
            }
            // 0.3秒遅延（エラー発生率を気持ち抑えるため）
            usleep(300000);
            // カウント+1
            $cnt++;
        }
    }
}

// json作成
// 文字コード設定
header('Content-Type: application/json; charset=UTF-8');

// メイン処理
$arr["result"] = $response_array;

// JSON_UNESCAPED_UNICODEでJSONを日本語で書く
// JSON_PRETTY_PRINTでJSONを成形する
print json_encode($arr, JSON_UNESCAPED_UNICODE | JSON_UNESCAPED_SLASHES | JSON_PRETTY_PRINT);
// print json_encode($arr, JSON_UNESCAPED_UNICODE);

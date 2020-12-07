<?php
/* =================
// 概要
// ================= */

// 選択したカテゴリーの中身全部出す！

/* =================
// プログラム
// ================= */

// デフォルトステータスをdefaultに設定
$status = "default";

// query_sql.phpを呼び出す
require('not_api/query_sql.php');

// connect_db.phpを呼び出す（データベースに接続）
// require('not_api/connect_db.php');
require('not_api/connect_database.php');

// search_dataを受け取ったら
if (isset($_POST['search_data'])) {
    // search_dataを受け取る
    $search_data = $_POST['search_data'];

    if ($search_data == "cat00") {
        // 全部のgoodsをすべて出す（ALL）
        $sql = "SELECT * FROM goods";

        // refrigerator_idを受け取ったら
        if (isset($_POST["refrigerator_id"])) {
            $refrigerator_id = $_POST["refrigerator_id"];

            // 全部のgoodsをすべて出す（ALL）
            $sql = "SELECT refrigerator_contents.goods_id, goods_name, goods_picture_name, content_number "
                . "FROM refrigerator_contents INNER JOIN goods ON refrigerator_contents.goods_id = goods.goods_id "
                . "WHERE refrigerator_id = '" . $refrigerator_id . "'";
        }

        // 接続したDBに対してSQL文を実行する
        $result = querySql($db, $sql);

        $array = array();
        // 全部出す
        foreach ($result as $row) {
            $goods_id = $row["goods_id"];
            $goods_name = $row["goods_name"];
            $goods_picture_name = $row["goods_picture_name"];
            if (isset($row["content_number"])) {
                $content_number = $row["content_number"];

                // 配列の中に入れる
                // array_push($array, $array_text);
                array_push(
                    $array,
                    array(
                    "goods_id" => $goods_id,
                    "goods_name" => $goods_name,
                    "goods_picture_name" => $goods_picture_name,
                    "content_number" => $content_number
                )
                );
            } else {
                // 配列の中に入れる
                // array_push($array, $array_text);
                array_push(
                    $array,
                    array(
                    "goods_id" => $goods_id,
                    "goods_name" => $goods_name,
                    "goods_picture_name" => $goods_picture_name
                )
                );
            }
        }

        $status = "yes";
    } elseif (
      $search_data == "cat01" || $search_data == "cat02" || $search_data == "cat03"
        || $search_data == "cat04" || $search_data == "cat05" || $search_data == "cat06" || $search_data == "cat07" || $search_data == "cat08"
    ) {
        // 該当カテゴリーのgoodsをすべて出す（カテゴリ別）
        $sql = "SELECT * FROM goods WHERE category_id = '" . $search_data . "'";

        // refrigerator_idを受け取ったら
        if (isset($_POST["refrigerator_id"])) {
            $refrigerator_id = $_POST["refrigerator_id"];

            // 全部のgoodsをすべて出す（ALL）
            $sql = "SELECT refrigerator_contents.goods_id, goods_name, goods_picture_name, content_number "
                . "FROM refrigerator_contents INNER JOIN goods ON refrigerator_contents.goods_id = goods.goods_id "
                . "WHERE refrigerator_id = '" . $refrigerator_id . "' "
                . "AND category_id = '" . $search_data . "'";
        }

        // 接続したDBに対してSQL文を実行する
        $result = querySql($db, $sql);

        $array = array();
        // 全部出す
        foreach ($result as $row) {
            $goods_id = $row["goods_id"];
            $goods_name =$row["goods_name"];
            $goods_picture_name =$row["goods_picture_name"];
            if (isset($row["content_number"])) {
                $content_number = $row["content_number"];
                // 配列の中に入れる
                // array_push($array, $array_text);
                array_push(
                    $array,
                    array(
                    "goods_id" => $goods_id,
                    "goods_name" => $goods_name,
                    "goods_picture_name" => $goods_picture_name,
                    "content_number" => $content_number
                )
                );
            } else {
                // 配列の中に入れる
                // array_push($array, $array_text);
                array_push(
                    $array,
                    array(
                    "goods_id" => $goods_id,
                    "goods_name" => $goods_name,
                    "goods_picture_name" => $goods_picture_name
                )
                );
            }
        }

        $status = "yes";
    } else {
        // 検索語句を含むレコードが存在委するか
        $sql = "SELECT COUNT(*) AS cnt FROM goods WHERE goods_name LIKE '%" . $search_data . "%'";

        // 接続したDBに対してSQL文を実行する
        $result = querySql($db, $sql);

        // pre_registrationにメールアドレスが登録済なら$row["cnt"]に1、未登録なら0が入る
        $row = mysqli_fetch_array($result);

        // レコードナシなら
        if ($row["cnt"] == 0) {
            $array = array();

            $status = "no_recode_error";
        } else {
            // 本番検索
            $sql = "SELECT * FROM goods WHERE goods_name LIKE '%" . $search_data . "%'";

            // 接続したDBに対してSQL文を実行する
            $result = querySql($db, $sql);

            $array = array();
            // 全部出す
            foreach ($result as $row) {
                $goods_id = $row["goods_id"];
                $goods_name =$row["goods_name"];
                $goods_picture_name =$row["goods_picture_name"];
                // 配列の中に入れる
                // array_push($array, $array_text);
                array_push(
                    $array,
                    array(
                    "goods_id" => $goods_id,
                    "goods_name" => $goods_name,
                    "goods_picture_name" => $goods_picture_name
                )
                );
            }

            $status = "yes";
        }
    }
} else {
    $array = array();

    $status = "error";
}

// json作成
// 文字コード設定
header('Content-Type: application/json; charset=UTF-8');

// メイン処理
$arr["status"] = $status;
$arr["data"] = $array;

// JSON_UNESCAPED_UNICODEでJSONを日本語で書く
// JSON_PRETTY_PRINTでJSONを成形する
print json_encode($arr, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
// print json_encode($arr, JSON_UNESCAPED_UNICODE);

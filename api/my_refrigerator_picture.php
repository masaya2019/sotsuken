<?php
// デフォルトステータスをdefaultに設定
$status = "default";

// query_sql.phpを呼び出す
require('not_api/query_sql.php');

// connect_db.phpを呼び出す（データベースに接続）
// require('not_api/connect_db.php');
require('not_api/connect_database.php');

// refrigerator_idを受け取ったら
if (isset($_POST["refrigerator_id"])) {
    $refrigerator_id = $_POST["refrigerator_id"];

    // refrigerator_idと一致するレコード件数取得
    $sql = "SELECT COUNT(*) AS cnt FROM refrigerator_picture WHERE refrigerator_id = '" . $refrigerator_id . "'";

    // 接続したDBに対してSQL文を実行する
    $result = querySql($db, $sql);

    // refrigerator_pictureにrefrigerator_idが登録済なら$row["cnt"]に1、未登録なら0が入る
    $row = mysqli_fetch_array($result);

    // refrigerator_pictureにrefrigerator_idが登録されているなら
    if ($row["cnt"] >= 1) {

        // refrigerator_idと一致するpicture_idを取得
        $sql = "SELECT picture_id FROM refrigerator_picture WHERE refrigerator_id = '" . $refrigerator_id . "'";

        // 接続したDBに対してSQL文を実行する
        $result = querySql($db, $sql);

        $array = array();
        // 全部出す
        foreach ($result as $row) {
            $picture_id = $row["picture_id"];

            // 配列の中に入れる
            // array_push($array, $array_text);
            array_push(
                $array,
                array(
                    "refrigerator_picture_name" => $refrigerator_id . "_" . $picture_id
                )
            );
        }

        $status = "yes";

    // refrigerator_pictureにrefrigerator_idが登録されていないなら
    } else {
        $array = array();

        $status = "no_recode_error";
    }
}


// メイン処理
$arr["status"] = $status;
$arr["data"] = $array;

// JSON_UNESCAPED_UNICODEでJSONを日本語で書く
// JSON_PRETTY_PRINTでJSONを成形する
print json_encode($arr, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
// print json_encode($arr, JSON_UNESCAPED_UNICODE);

<?php
// デフォルトステータスをdefaultに設定
$status = "default";

// query_sql.phpを呼び出す
require('not_api/query_sql.php');

// connect_db.phpを呼び出す（データベースに接続）
// require('not_api/connect_db.php');
require('not_api/connect_database.php');


// データを受け取ったら
if (isset($_POST["refrigerator_id"]) && isset($_POST["picture_name"])) {
    $refrigerator_id = $_POST["refrigerator_id"];
    $picture_name = $_POST["picture_name"];

    // picture_id
    $picture_id = mb_substr($picture_name, 6);

    $sql = "SELECT COUNT(*) AS cnt FROM refrigerator_picture "
        . "WHERE refrigerator_id = '" . $refrigerator_id . "' "
        . "AND picture_id = '" . $picture_id . "'";

    // 接続したDBに対してSQL文を実行する
    $result = querySql($db, $sql);

    // refrigerator_pictureに
    $row = mysqli_fetch_array($result);

    // refrigerator_pictureにレコードが存在するなら
    if ($row["cnt"] == 1) {
        $sql = "DELETE FROM refrigerator_picture "
        . "WHERE refrigerator_id = '" . $refrigerator_id . "' "
        . "AND picture_id = '" . $picture_id . "'";

        // 接続したDBに対してSQL文を実行する
        $result = querySql($db, $sql);

        $status = "yes";
    }

    // メイン処理
    $arr["status"] = $status;

    // JSON_UNESCAPED_UNICODEでJSONを日本語で書く
    // JSON_PRETTY_PRINTでJSONを成形する
    print json_encode($arr, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
}

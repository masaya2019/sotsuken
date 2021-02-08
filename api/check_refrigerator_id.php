<?php
// デフォルトステータスをdefaultに設定
$status = "default";
$refrigerator_id = "default";

//使用者の'mail_address'を取得
// $mail_address = $_POST['mail_address'];
// $mail_address = "bjmk1290313@gn.iwasaki.ac.jp";
// $mail_address = "test@gn.iwasaki.ac.jp";

// query_sql.phpを呼び出す
require('not_api/query_sql.php');

// connect_db.phpを呼び出す（データベースに接続）
// require('not_api/connect_db.php');
require('not_api/connect_database.php');

//refrigeratorテーブルに使用者の'mail_address'があるか確認して結果を$rowに格納
$sql = "SELECT EXISTS(SELECT * FROM refrigerator WHERE mail_address = '". $mail_address ."') AS num;";
$result = querySql($db, $sql);
$row = mysqli_fetch_assoc($result);

//レコードの真偽判定
if ($row["num"]) {
    //レコードが存在する場合、{ “status” : “yes” }を返す。
    $status = "yes";
    $sql = "SELECT refrigerator_id FROM refrigerator WHERE mail_address = '" . $mail_address . "' LIMIT 1";
    $result = querySql($db, $sql);
    $row = mysqli_fetch_assoc($result);
    $refrigerator_id = $row["refrigerator_id"];
} else {
    //レコードが存在しないときは、{ “status” : “no_recode” }を返す。
    $status = "no_recode";
}

// メイン処理
$arr["status"] = $status;
$arr["refrigerator_id"] = $refrigerator_id;

// JSON_UNESCAPED_UNICODEでJSONを日本語で書く
// JSON_PRETTY_PRINTでJSONを成形する
print json_encode($arr, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);

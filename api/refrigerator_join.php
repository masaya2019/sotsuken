<?php
/* =================
// 概要
// ================= */

// 同じ冷蔵庫に招待するAPI
// 冷蔵庫ID、メールアドレスを受け取る
// 受け取ったこのrefrigerator_idとmail_addressをrefrigeratorテーブルに追加する。
// レコードが存在する場合、{ “status” : “yes” }を返す。

/* =================
// プログラム
// ================= */

// デフォルトステータスをdefaultに設定
$status = "default";

// connect_db.phpを呼び出す（データベースに接続）
// require('not_api/connect_db.php');
require('not_api/connect_database.php');

// query_sql.phpを呼び出す
require('not_api/query_sql.php');


// 冷蔵庫ID、メールアドレスを受け取る
$refrigerator_id = $_POST["refrigerator_id"];
$mail_address = $_POST["mail_address"];

// refrigeratorテーブルに使用者の'refrigerator_id'があるか確認して結果を$rowに格納
$sql = "SELECT EXISTS(SELECT * FROM refrigerator WHERE refrigerator_id = '". $refrigerator_id ."' AND mail_address = '". $mail_address ."') AS num;";
$result = querySql($db, $sql);
$row = mysqli_fetch_assoc($result);

// レコードの真偽判定
if ($row["num"]) {
    // レコードが存在する場合、{ “status” : “yes” }を返す。
    $status = "yes";
} else {
    // レコードが存在しないときはレコードを追加して、{ “status” : “yes” }を返す。
    $sql = "INSERT INTO refrigerator (refrigerator_id,mail_address) VALUES ('". $refrigerator_id ."','". $mail_address ."');";
    $result = querySql($db, $sql);
    $status = "yes";
}

// JSON用のデータにする。
$data = [
    'status' => $status
];

// jsonで結果を返す
header("Content-Type: application/json; charset=utf-8");
echo json_encode($data);

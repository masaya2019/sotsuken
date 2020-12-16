<?php
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
$new_refrigerator_name = $_POST["new_refrigerator_name"];

if (isset($_POST["new_refrigerator_name"])) {
    $sql = "UPDATE refrigerator SET refrigerator_name = '" . $new_refrigerator_name . "' "
        . "WHERE refrigerator_id = '" . $refrigerator_id . "' "
        . "AND mail_address = '" . $mail_address . "'";
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

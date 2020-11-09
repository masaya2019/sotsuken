<?php
/* =================
 // 概要
 // ================= */

 //メモの内容を取得するAPI
 // レコードが存在する場合、{ “status” : “yes” }を返す。　
 //memoテーブルのrefrigerator_id、mail_address、datetimeと一致するmemo_contentsを返す。
 //レコードが存在しないときは、{ “status” : “no_recode” }を返す。

 /* =================
 // プログラム
 // ================= */

// デフォルトステータスをdefaultに設定
$status = "default";

//使用者の'refrigerator_id'を取得
$refrigerator_id = $_POST['refrigerator_id'];
$mail_address = $_POST['mail_address'];
$datetime = $_POST['datetime'];

// query_sql.phpを呼び出す
require('not_api/query_sql.php');

// connect_db.phpを呼び出す（データベースに接続）
require('not_api/connect_db.php');

//memoテーブルにrefrigerator_id、mail_address、datetimeと一致するレコードがあるか確認して結果を$rowに格納
$sql = "SELECT EXISTS(SELECT * FROM memo WHERE refrigerator_id = '". $refrigerator_id ."' AND mail_address = '". $mail_address ."' AND datetime = '". $datetime ."') AS num;";
$result = querySql($db, $sql);
$row = mysqli_fetch_assoc($result);

//レコードの真偽判定
if ($row["num"]) {
    //レコードが存在する場合、{ “status” : “yes” }を返す。
    $status = "yes";
} else {
    //レコードが存在しないときは、{ “status” : “no_recode” }を返す。
    $status = "no_recode";
}

//$data にJSON形式で結果を格納
$data = [
    'status' => $status
];

//$data にJSON形式で結果を格納
if ($status == "yes") {
    //{“status” : “yes” }なら'refrigerator_id'、'mail_address'、'datetime'と一致する'memo_contents'を返す。
    
    //'refrigerator_id'、'mail_address'、'datetime'に一致するレコードを抽出
    $sql = "SELECT memo_title, memo_contents FROM memo WHERE refrigerator_id = '". $refrigerator_id ."' AND mail_address = '". $mail_address ."' AND datetime = '". $datetime ."';";
    $result = querySql($db, $sql);
    $row = mysqli_fetch_assoc($result);
    $memo_title = $row["memo_title"];
    $memo_contents = $row["memo_contents"];

    //$data にJSON形式で結果を格納
    $data = [
        'status' => $status,
        'memo_title' => $memo_title,
        'memo_contents' => $memo_contents
    ];
}

//JSONを送信
header("Content-Type: application/json; charset=utf-8");
echo json_encode($data);

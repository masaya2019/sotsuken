<?php
/* =================
 // 概要
 // ================= */

 //メモを追加するAPI
 //memoテーブルにrefrigerator_id、mail_address、datetime、memo_title、memo_contentsを新しく登録する。
 //最後に、{ “status” : “yes” }を返す。

 /* =================
 // プログラム
 // ================= */

// デフォルトステータスをdefaultに設定
$status = "default";

//使用者の'refrigerator_id'を取得
$refrigerator_id = $_POST['refrigerator_id'];
$mail_address = $_POST['mail_address'];
$memo_title = $_POST['memo_title'];
$memo_contents = $_POST['memo_contents'];

// query_sql.phpを呼び出す
require('not_api/query_sql.php');

// connect_db.phpを呼び出す（データベースに接続）
// require('not_api/connect_db.php');
require('not_api/connect_database.php');

//get_now_datetime.phpを呼び出し
require('not_api/get_now_datetime.php');

//更新時刻を取得
$datetime = get_now_datetime();

//memoテーブルにrefrigerator_id、mail_address、datetimeと一致するレコードがあるか確認して結果を$rowに格納
$sql = "INSERT INTO memo (refrigerator_id,mail_address,datetime,memo_title,memo_contents) VALUES ('". $refrigerator_id ."','". $mail_address ."','". $datetime ."','". $memo_title ."','". $memo_contents ."');";
$result = querySql($db, $sql);

//{ “status” : “yes” }にする
$status = 'yes';

//$data にJSON形式で結果を格納
$data = [
    'status' => $status
];

//JSONを送信
header("Content-Type: application/json; charset=utf-8");
echo json_encode($data);

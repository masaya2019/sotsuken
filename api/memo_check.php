<?php
/* =================
 // 概要
 // ================= */

 //memoテーブルにレコードがあるかを確認するAPI
 // レコードが存在する場合、{ “status” : “yes” }を返す。　
 //memoテーブルのrefrigerator_idと一致するmail_address、datetime、memo_titleを返す。
 //レコードが存在しないときは、{ “status” : “no_recode” }を返す。

 /* =================
 // プログラム
 // ================= */

// デフォルトステータスをdefaultに設定
$status = "default";

//使用者の'refrigerator_id'を取得
$refrigerator_id = $_POST['refrigerator_id'];

// query_sql.phpを呼び出す
require('not_api/query_sql.php');

// connect_db.phpを呼び出す（データベースに接続）
require('not_api/connect_db.php');

//memoテーブルに使用者の'refrigerator_id'があるか確認して結果を$rowに格納
$sql = "SELECT EXISTS(SELECT * FROM memo WHERE refrigerator_id = '". $refrigerator_id ."') AS num;";
$result = querySql($db, $sql);
$row = mysqli_fetch_assoc($result);

//レコードの真偽判定
if($row["num"]){
	//レコードが存在する場合、{ “status” : “yes” }を返す。
	$status = "yes";

	//使用者の'refrigerator_id'に一致するレコードを日付順に抽出
	$sql = "SELECT * FROM memo WHERE refrigerator_id = '". $refrigerator_id ."' ORDER BY datetime DESC;";
	$result = querySql($db, $sql);

	//レコードを配列に格納
	$cnt = 0;
	while($row = mysqli_fetch_assoc($result)){
		$mail_address[$cnt] = $row["mail_address"];
		$datetime[$cnt] = $row["datetime"];
		$memo_title[$cnt] = $row["memo_title"];
		$memo_contents[$cnt] = $row["memo_contents"];
		$cnt++;
	}
}else{
	//レコードが存在しないときは、{ “status” : “no_recode” }を返す。
	$status = "no_recode";
}

//$data にJSON形式で結果を格納
if($status == "yes"){
	//{“status” : “yes” }なら'refrigerator_id'と一致する'mail_address'、'datetime'、'memo_title'を返す。
	$data = [
		$status,
		$mail_address,
		$datetime,
		$memo_title,
		$memo_contents
	];
}else{
	$data = [
		$status
	];
}

//JSONを送信
header("Content-Type: application/json; charset=utf-8");
echo json_encode($data);
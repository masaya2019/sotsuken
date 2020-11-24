<?php
/* =================
 // 概要
 // ================= */

// データベースにメールアドレス,ニックネーム,パスワードを登録するAPI

/* =================
 // プログラム
 // ================= */

$address = $_POST["mail_address"];
$name = $_POST["user_name"];
$pass = $_POST["password"];

// $db = mysqli_connect('localhost', 'root', '', 'comasy') or die(mysqli_connect_error());
require('not_api/connect_database.php');

$sql = "INSERT INTO member_information VALUES('".$address."','".$name."','".$pass."')";

mysqli_set_charset($db, "utf8");

$r = mysqli_query($db, $sql);

if ($r) {
    $sql2 = 'UPDATE pre_registration SET flag=1 WHERE mail_address="'.$address.'"';
    mysqli_query($db, $sql2);
}

mysqli_close($db);

$status = "yes";

// json作成
// 文字コード設定
header('Content-Type: application/json; charset=UTF-8');

// メイン処理
$arr["status"] = $status;

// JSON_UNESCAPED_UNICODEでJSONを日本語で書く
// JSON_PRETTY_PRINTでJSONを成形する
print json_encode($arr, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
// print json_encode($arr, JSON_UNESCAPED_UNICODE);

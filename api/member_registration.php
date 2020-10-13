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

$db = mysqli_connect('localhost','root','','comasy') or die(mysqli_connect_error());

$sql = "INSERT INTO member_information VALUES('".$address."','".$name."','".$pass."')";

mysqli_set_charset($db,"utf8");

$r = mysqli_query($db, $sql);

if($r){
    $sql2 = 'UPDATE pre_registration SET flag=1 WHERE mail_address="'.$address.'"';
    mysqli_query($db, $sql2);
}

mysqli_close($db);
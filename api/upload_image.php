<?php
/* =================
 // 概要
 // ================= */

 //冷蔵庫の写真を保存するAPI
 //保存する画像を受け取る。
 //画像をtmp_imagesフォルダに一時保存。
 //新しいpicture_idを生成。
 //refrigerator_pictureテーブルにrefrigerator_id,picture_id,refrigerator_picture_nameを新しく登録する。
 //tmp_imagesフォルダからimagesフォルダに画像を移動させる。
 //正常に画像を移動させられたら、{ “status” : “yes” }を返す。
 //異常がでた場合、{ “status” : “error” }を返す。

 /* =================
 // プログラム
 // ================= */

// デフォルトステータスをdefaultに設定
$status = "default";

// 送られた画像tmpフォルダに一時保存
$dir = "tmp_images/" . $_FILES['image']['name'];
move_uploaded_file($_FILES['image']['tmp_name'], $dir);

// 保存したファイル名を取得
$file_name = $_FILES['image']['name'];

// 冷蔵庫IDの取得
$refrigerator_id = substr($file_name, 0, 5);

// query_sql.phpを呼び出す
require('not_api/query_sql.php');

// connect_db.phpを呼び出す（データベースに接続）
// require('not_api/connect_db.php');
require('not_api/connect_database.php');

// refrigerator_pictureテーブルに使用者の'refrigerator_id'があるか確認して結果を$rowに格納
$sql = "SELECT EXISTS(SELECT * FROM refrigerator_picture WHERE refrigerator_id = '". $refrigerator_id ."') AS num;";
$result = querySql($db, $sql);
$row = mysqli_fetch_assoc($result);

// レコードの真偽判定
if ($row["num"]) {
    // レコードが存在するときの処理。
    
    // 一番最新のpicture_idを取得
    $sql = "SELECT MAX(picture_id) FROM refrigerator_picture WHERE refrigerator_id = '". $refrigerator_id ."';";
    $result = querySql($db, $sql);
    $row = mysqli_fetch_assoc($result);
    $picture_id = $row["MAX(picture_id)"];

    // 取得したpicture_idを加算
    $picture_id = ++$picture_id;

    // $refrigerator と picture_id から新しい refrigerator_picture_nameを作成
    $refrigerator_picture_name = $refrigerator_id ."_". $picture_id .".png";
} else {
    // レコードが存在しないときの処理。
    
    // picture_id を"p0001"で新しく作成
    $picture_id = "p0001";
    
    // $refrigerator と picture_id から新しい refrigerator_picture_nameを作成
    $refrigerator_picture_name = $refrigerator_id ."_". $picture_id .".png";
}

//新しいファイル名でimagesフォルダに保存
if (rename($dir, "images/". $refrigerator_picture_name)) {
    $status = "yes";
} else {
    $status = "error";
}

// DBに新しいレコードを作成
$sql = "INSERT INTO refrigerator_picture (refrigerator_id,picture_id,refrigerator_picture_name) VALUES ('". $refrigerator_id ."','". $picture_id ."','". $refrigerator_picture_name ."');";
$result = querySql($db, $sql);

<?php
/* =================
 // 概要
 // ================= */

 // 切り替える冷蔵庫を探すAPI（詳細検討中）
 // mail_addressを受け取ったら、refrigeratorテーブルから該当するrefrigerator_idを返す。
 // レコードが存在する場合、{ “status” : “yes” }を返す。

 /* =================
 // プログラム
 // ================= */

// デフォルトステータスをdefaultに設定
$status = "default";

//使用者の'mail_address'を取得
$mail_address = $_POST['mail_address'];

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
} else {
    //レコードが存在しないときは、{ “status” : “no_recode” }を返す。
    $status = "no_recode";
}

// JSON用の配列を作成
$data = array(
    'status' => $status,
    'data' => array()
);

//$data にJSON形式で結果を格納
if ($status == "yes") {
    //{“status” : “yes” }なら'mail_address'と一致する'refrigerator_id'を返す。

    //使用者の'mail_address'に一致するレコードを日付順に抽出
    $sql = "SELECT * FROM refrigerator WHERE mail_address = '". $mail_address ."' ORDER BY refrigerator_id;";
    $result = querySql($db, $sql);

    //レコードを配列に格納
    while ($row = mysqli_fetch_assoc($result)) {
        $refrigerator_id = $row["refrigerator_id"];
        $refrigerator_name = $row["refrigerator_name"];

        array_push(
            $data['data'],
            array(
              'refrigerator_id' => $refrigerator_id,
              'refrigerator_name' => $refrigerator_name
            )
        );
    }
}

//JSONを送信
header("Content-Type: application/json; charset=utf-8");
echo json_encode($data);

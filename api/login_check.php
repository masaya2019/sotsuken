<?php
$mail_address = $_POST["mail_address"];
$password = $_POST["password"];

// デフォルトステータスをdefaultに設定
$status = "default";

// query_sql.phpを呼び出す
require('not_api/query_sql.php');

// connect_db.phpを呼び出す（データベースに接続）
require('not_api/connect_db.php');

// member_informationにメールアドレスが登録済かどうかを確認する
$sql = "SELECT COUNT(*) AS cnt FROM member_information WHERE mail_address = '" . $mail_address . "'";

// 接続したDBに対してSQL文を実行する
$result = querySql($db, $sql);

// member_informationにメールアドレスが登録済なら$row["cnt"]に1、未登録なら0が入る
$row = mysqli_fetch_array($result);

// pre_registrationにメールアドレスが登録されているなら
if ($row["cnt"] == 1) {
    // member_informationからメールアドレスに対応するパスワードを取得
    $sql = "SELECT password FROM member_information WHERE mail_address = '" . $mail_address . "'";

    // 接続したDBに対してSQL文を実行する
    $result = querySql($db, $sql);

    // $row["password"]にパスワードが入る
    $row = mysqli_fetch_array($result);

    // パスワードが一致していたら
    if ($row["password"] == $password) {
        // statusをyesに変更
        $status = "yes";
    // パスワードが違ったら
    } else {
        // statusをpassword_errorに変更
        $status = "password_error";
    }

    // pre_registrationにメールアドレスが未登録なら
} else {
    // statusをaddress_errorに変更
    $status = "address_error";
}

// json作成
// 文字コード設定
header('Content-Type: application/json; charset=UTF-8');

// メイン処理
$arr["status"] = $status;

// JSON_UNESCAPED_UNICODEでJSONを日本語で書く
// JSON_PRETTY_PRINTでJSONを成形する
print json_encode($arr, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
// print json_encode($arr, JSON_UNESCAPED_UNICODE);

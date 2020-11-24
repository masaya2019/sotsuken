<?php
/* =================
// 概要
// ================= */

// メールアドレスと認証コードをチェックするAPI
// 結果はjson形式で
// メールアドレスアドレスが正常で、認証コードが合っている場合　{ "status" : "yes" }
// メールアドレスアドレスが正常で、認証コードが違っている場合　{ "status" : "different_token_error" }
// メールアドレスアドレスがデータベースにない場合　{ "status" : "no_address_error" }
// メールアドレスのに形式になっていない場合　{ "status" :　"address_type_error" }
// 認証コードの文字数が違う場合　{ "status" : "token_error" }
// を返す


/* =================
// プログラム
// ================= */

// デフォルトステータスをdefaultに設定
$status = "default";

// check_mail_address.phpを呼び出す
require('not_api/check_mail_address.php');

// check_token_type.phpを呼び出す
require('not_api/check_token_type.php');

// query_sql.phpを呼び出す
require('not_api/query_sql.php');

// mail_addressが存在し、メールアドレスの形式になっている
if (isset($_POST['mail_address']) && checkAddress($_POST['mail_address'])) {
    // tokenが存在し、4桁の数字である
    if (isset($_POST['token']) && checkTokenType($_POST['token'])) {
        $mail_address = $_POST['mail_address'];
        $token = $_POST['token'];

        // connect_db.phpを呼び出す（データベースに接続）
        // require('not_api/connect_db.php');
        require('not_api/connect_database.php');

        // pre_registrationにメールアドレスが登録済かどうかを確認する
        $sql = "SELECT COUNT(*) AS cnt FROM pre_registration WHERE mail_address = '" . $mail_address . "'";

        // 接続したDBに対してSQL文を実行する
        $result = querySql($db, $sql);

        // pre_registrationにメールアドレスが登録済なら$row["cnt"]に1、未登録なら0が入る
        $row = mysqli_fetch_array($result);

        // pre_registrationにメールアドレスが登録済なら
        if ($row["cnt"] == 1) {
            $sql = "SELECT token FROM pre_registration WHERE mail_address = '" . $mail_address . "'";

            // 接続したDBに対してSQL文を実行する
            $result = querySql($db, $sql);

            // データベースからtoken取得$row["token"];
            $row = mysqli_fetch_array($result);
            $correct_token = $row["token"];

            // 認証コードが一致したら
            if ($correct_token == $token) {
                $status = "yes";

            // 認証コードが一致しないなら
            } else {
                $status = "different_token_error";
            }

            // pre_registrationにメールアドレスが未登録なら
        } else {
            $status = "no_address_error";
        }

        // DBを閉じる
        mysqli_close($db);
    } else {
        $status = "token_error";
    }

    // メールアドレスが不正な場合
} else {
    $status = "address_type_error";
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

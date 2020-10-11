<?php
/* =================
// 概要
// ================= */

// メールアドレスから認証を生成し、認証用コードをメールで送るAPI
// メールアドレスからpre_registrationテーブルのデータを作成する
// 結果はjson形式で
// アドレスが正常で、メールを送った場合　{ "status" :　"yes" }
// すでに登録されているアドレスの場合　{ "status" :　"used_address_error" }
// メールアドレスのに形式になっていない場合　{ "status" :　"address_type_error" }
// を返す


/* =================
// プログラム
// ================= */

// デフォルトステータスをdefaultに設定
$status = "default";

// check_mail_address.phpを呼び出す
require('not_api/check_mail_address.php');

// query_sql.phpを呼び出す
require('not_api/query_sql.php');

// mail_addressが存在し、メールアドレスの形式になっているなら
if (isset($_POST['mail_address']) && checkAddress($_POST['mail_address'])) {
    $mail_address = htmlspecialchars($_POST['mail_address']);

    // 仮登録日時を作成
    require('not_api/get_now_datetime.php');
    $pre_registration_date = get_now_datetime();

    // connect_db.phpを呼び出す（データベースに接続）
    require('not_api/connect_db.php');

    // pre_registrationにメールアドレスが登録済かどうかを確認する
    $sql = "SELECT COUNT(*) AS cnt FROM pre_registration WHERE mail_address = '" . $mail_address . "'";

    // 接続したDBに対してSQL文を実行する
    $result = querySql($db, $sql);

    // pre_registrationにメールアドレスが登録済なら$row["cnt"]に1、未登録なら0が入る
    $row = mysqli_fetch_array($result);

    // pre_registrationにメールアドレスが未登録なら
    if ($row["cnt"] == 0) {
        // 4桁のtokenを作成
        $token = getToken();

        // pre_registrationにレコードを登録する（flagはデフォルトで0設定）
        $sql = "INSERT INTO pre_registration(mail_address, token, datetime) VALUES('"
            . $mail_address . "', '"
            . $token . "', '"
            . $pre_registration_date . "')";

        // 接続したDBに対してSQL文を実行する
        $result = querySql($db, $sql);

        // tokenをメールアドレスに送る
        sendMail($mail_address, $token);

        // statusをyesに変更
        $status = "yes";

    // pre_registrationにメールアドレスが登録済かつflag=0（未登録）なら
    } elseif ($row["cnt"] == 1 && checkFlag($mail_address, $db) == 0) {

        // 仮登録から1時間過ぎていないなら
        if (limitTime($mail_address, $db) == true) {
            $sql = "SELECT token FROM pre_registration WHERE mail_address = '" . $mail_address . "'";

            // 接続したDBに対してSQL文を実行する
            $result = querySql($db, $sql);

            // データベースからtoken取得$row["token"];
            $row = mysqli_fetch_array($result);
            $token = $row["token"];

            // データベースのtokenをメールに送る
            sendMail($mail_address, $token);

            // statusをyesに変更
            $status = "yes";


        // 仮登録から1時間過ぎているなら
        } else {

            // 新しいtokenを作成
            $token = getToken();

            // 現在時刻を作成
            $pre_registration_date = get_now_datetime();

            // 新しいtokenと現在時刻をデータベースに更新
            $sql = "UPDATE pre_registration SET "
                . "token = '" . $token . "', "
                . "datetime = '" . $pre_registration_date . "' "
                . "WHERE mail_address = '"
                . $mail_address . "'";

            // 接続したDBに対してSQL文を実行する
            $result = querySql($db, $sql);

            // tokenをメールアドレスに送る
            sendMail($mail_address, $token);

            // statusをyesに変更
            $status = "yes";
        }

        // pre_registrationにメールアドレスが登録済かつflag=1（登録済）なら
    } elseif ($row["cnt"] == 1 && checkFlag($mail_address, $db) == 1) {

        // 既に使用されている（登録済）アドレス
        // statusをr_errorに変更
        $status = "used_address_error";
    }

    // DBを閉じる
    mysqli_close($db);

// メールアドレスが不正な場合
} else {
    // statusをm_errorに変更
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


/* ================================================================================================
// 関数
// ============================================================================================= */

// 4桁のtokenを作成
function getToken()
{
    $token = '';
    for ($i = 0; $i < 4; $i++) {
        $token .= mt_rand(0, 9);
    }
    return $token;
}

// フラグの値を返す
function checkFlag($mail_address, $db)
{
    // flagの値を確認する
    $sql = "SELECT flag FROM pre_registration WHERE mail_address = '" . $mail_address . "'";

    // 接続したDBに対してSQL文を実行する
    $result = querySql($db, $sql);


    // 未登録なら$row["flag"]に0、登録済みなら1が入る
    $row = mysqli_fetch_array($result);
    return $row["flag"];
}

// 仮登録から1時間過ぎているか
function limitTime($mail_address, $db)
{
    // flagの値を確認する
    $sql = "SELECT datetime FROM pre_registration WHERE mail_address = '" . $mail_address . "'";

    // 接続したDBに対してSQL文を実行する
    $result = querySql($db, $sql);


    // 仮登録の時刻$row["datetime"];
    $row = mysqli_fetch_array($result);

    // 現在時刻と仮登録の時刻の時間差を計算
    $diff_time = strtotime(get_now_datetime()) - strtotime($row["datetime"]);

    // 現在時刻と仮登録の時刻の時間差 <= 1時間(3600)ならtrue、そうでないならfalse
    if ($diff_time <= 3600) {
        return true;
    } else {
        return false;
    }
}

// tokenをメールで送る
function sendMail($mail_address, $token)
{
    require('not_api/send_mail.php');
    sendTokenMail($mail_address, $token);
}

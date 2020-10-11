<?php
// HPMailer のクラスをグローバル名前空間（global namespace）にインポート
// スクリプトの先頭で宣言する必要があります
use PHPMailer\PHPMailer\PHPMailer;
use PHPMailer\PHPMailer\SMTP;
use PHPMailer\PHPMailer\Exception;

function sendTokenMail($mail_address, $token)
{
    // https://www.webdesignleaves.com/pr/php/php_phpmailer.php

    // Composer のオートローダーの読み込み（ファイルの位置によりパスを適宜変更）
    require 'php_mailer/vendor/autoload.php';

    //mbstring の日本語設定
    mb_language("japanese");
    mb_internal_encoding("UTF-8");

    // インスタンスを生成（引数に true を指定して例外 Exception を有効に）
    $mail = new PHPMailer(true);

    //日本語用設定
    $mail->CharSet = "iso-2022-jp";
    $mail->Encoding = "7bit";

    try {
        //サーバの設定

        // // デバグの出力を有効に（テスト環境での検証用）
        // $mail->SMTPDebug = SMTP::DEBUG_SERVER;
        // SMTP を使用

        $mail->isSMTP();
        // ★★★ Gmail SMTP サーバーを指定

        $mail->Host       = 'smtp.gmail.com';
        // SMTP authentication を有効に

        $mail->SMTPAuth   = true;

        // ★★★ Gmail ユーザ名
        $mail->Username   = 'info.comasy@gmail.com';

        require 'gmail_password.php';
        // ★★★ Gmail パスワード
        $mail->Password   = $gmail_password;

        // ★★★ 暗号化（TLS)を有効に
        $mail->SMTPSecure = PHPMailer::ENCRYPTION_STARTTLS;

        //★★★ ポートは 587
        $mail->Port = 587;

        //受信者設定
        //差出人アドレス, 差出人名
        $mail->setFrom('info.comasy@gmail.com', mb_encode_mimeheader('Comasy Information Mail'));

        // 受信者アドレス, 受信者名（受信者名はオプション）
        $mail->addAddress($mail_address);

        // コンテンツ設定
        // HTML形式を指定
        $mail->isHTML(true);

        //メール表題（タイトル）
        $mail->Subject = mb_encode_mimeheader('認証コードの送信');

        //本文（HTML用）
        $mail->Body  = mb_convert_encoding('認証コードは <b>' . $token . '</b>' . "です", "JIS", "UTF-8");

        //テキスト表示の本文
        $mail->AltBody = mb_convert_encoding('プレインテキストメッセージ non-HTML mail clients', "JIS", "UTF-8");

        //送信
        $mail->send();
        // echo 'Message has been sent';
    } catch (Exception $e) {
        // echo "Message could not be sent. Mailer Error: {$mail->ErrorInfo}";
    }
}

<?php
// メールアドレスの形式になっているか調べる
// メールアドレスの形式になっている時にtrue、メールアドレスの形式になっていないときはfalseを返却する
function checkAddress($mail_address)
{
    $reg_str = "/^([a-zA-Z0-9])+([a-zA-Z0-9\._-])*@([a-zA-Z0-9_-])+([a-zA-Z0-9\._-]+)+$/";
    if (preg_match($reg_str, $mail_address)) {
        // echo "正しいメールアドレスです\n";
        return true;
    } else {
        // echo "正しくないメールアドレスです\n";
        return false;
    }
}

<?php
// tokenが4桁の数字になっているか調べる
// 4桁の数字の時にtrue、4桁の数字になっていないときはfalseを返却する
function checkTokenType($token)
{
    // 4桁の数字？
    if (is_numeric($token) && mb_strlen($token) == 4) {
        return true;
    } else {
        return false;
    }
}

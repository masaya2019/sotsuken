<?php
// 接続したDBに対してSQL文を実行する
function querySql($db, $sql)
{
    $result = mysqli_query($db, $sql);
    if ($result == false) {
        exit();
    }
    return $result;
}

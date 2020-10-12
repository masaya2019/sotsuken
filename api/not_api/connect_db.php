<?php
    // データベースに接続する
    // "host", "username", "password", "dbname"
    $db = mysqli_connect("localhost", "root", "", "comasy")
        or die(mysqli_connect_error());
    mysqli_set_charset($db, "utf-8");

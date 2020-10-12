<?php
    // 現在日時を作成
    function get_now_datetime()
    {
        date_default_timezone_set('Asia/Tokyo');
        return date('Y-m-d H:i:s');
    }

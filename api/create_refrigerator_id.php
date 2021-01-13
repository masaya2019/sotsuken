<?php
// デフォルトステータスをdefaultに設定
$status = "default";
$refrigerator_id = "default";

// mail_addressを受け取ったら
if (isset($_POST['mail_address'])) {
    $mail_address = $_POST['mail_address'];

    // query_sql.phpを呼び出す
    require('not_api/query_sql.php');

    // connect_db.phpを呼び出す（データベースに接続）
    // require('not_api/connect_db.php');
    require('not_api/connect_database.php');


    // refrigeratorテーブルから全てのrefrigerator_idを取得
    $sql = "SELECT DISTINCT refrigerator_id FROM refrigerator";

    // 接続したDBに対してSQL文を実行する
    $result = querySql($db, $sql);

    // 仮の最大値を0にする
    $max = 0;

    // 最大値を探す
    foreach ($result as $row) {
        // refrigerator_idの先頭の文字(r)を取り、INTにする
        $refrigerator_id_number = (int)substr($row["refrigerator_id"], 1);

        // 仮の最大値より大きければ更新
        if ($max < $refrigerator_id_number) {
            $max = $refrigerator_id_number;
        }
    }

    // 現在の最大値より+1大きいのが新しいrefrigerator_id
    $new_refrigerator_id = $max + 1;

    // String型に変換
    $new_refrigerator_id_str = (String)$new_refrigerator_id;

    // もし、現在のrefrigerator_idが4桁に満たなければ
    if (mb_strlen($new_refrigerator_id_str) < 4) {
        // 0を追加し、4桁にする
        for ($i = 0; $i < 4 - mb_strlen((String)$new_refrigerator_id); $i++) {
            $new_refrigerator_id_str = "0" . $new_refrigerator_id_str;
        }
    }

    // 新しいrefrigerator_idを発行
    $refrigerator_id = "r" . $new_refrigerator_id_str;

    // 仮の冷蔵庫名を作成
    // member_informationテーブルからperson_nameを取得
    $sql = "SELECT DISTINCT person_name FROM member_information WHERE mail_address = '" . $mail_address . "'";

    // 接続したDBに対してSQL文を実行する
    $result = querySql($db, $sql);

    $row = mysqli_fetch_assoc($result);

    // レコードの真偽判定
    if ($row["person_name"]) {
        $refrigerator_name = $row["person_name"] . "の冷蔵庫";
    }

    // refrigeratorテーブルにmail_addressとrefrigerator_idを登録する
    $sql = "INSERT INTO refrigerator(refrigerator_id, mail_address,refrigerator_name) VALUES('"
        . $refrigerator_id . "', '"
        . $mail_address . "', '"
        . $refrigerator_name . "')";

    // 接続したDBに対してSQL文を実行する
    $result = querySql($db, $sql);

    $status = "yes";
}

// メイン処理
$arr["status"] = $status;
$arr["refrigerator_id"] = $refrigerator_id;

// JSON_UNESCAPED_UNICODEでJSONを日本語で書く
// JSON_PRETTY_PRINTでJSONを成形する
print json_encode($arr, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);

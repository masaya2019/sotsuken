<?php
// デフォルトステータスをdefaultに設定
$status = "default";

// refrigerator_idとgoods_idとadd_quantityを受け取った時
if (isset($_POST['refrigerator_id']) && isset($_POST['goods_id']) && isset($_POST['add_quantity'])) {
    $refrigerator_id = $_POST['refrigerator_id'];
    $goods_id = $_POST['goods_id'];
    $add_quantity = (Int)$_POST['add_quantity'];

    // query_sql.phpを呼び出す
    require('not_api/query_sql.php');

    // connect_db.phpを呼び出す（データベースに接続）
    require('not_api/connect_db.php');

    // refrigerator_contentsテーブルにrefrigerator_idのgoods_idのレコードあるかどうか
    $sql = "SELECT COUNT(*) AS cnt FROM refrigerator_contents "
        . "WHERE refrigerator_id = '" . $refrigerator_id . "' "
        . "AND goods_id = '" . $goods_id . "'";

    // 接続したDBに対してSQL文を実行する
    $result = querySql($db, $sql);

    // refrigerator_contentsに登録済なら$row["cnt"]に1、未登録なら0が入る
    $row = mysqli_fetch_array($result);

    // refrigerator_contents未登録なら
    if ($row["cnt"] == 0) {
        $sql = "INSERT INTO refrigerator_contents(refrigerator_id, goods_id, content_number) VALUES('"
            . $refrigerator_id . "', '"
            . $goods_id . "', "
            . $add_quantity . ")";

        // 接続したDBに対してSQL文を実行する
        $result = querySql($db, $sql);

        $status = "yes";
    // refrigerator_contents登録済みなら
    } else {
        // 元々あった数
        $sql = "SELECT content_number FROM refrigerator_contents "
        . "WHERE refrigerator_id = '" . $refrigerator_id . "' "
        . "AND goods_id = '" . $goods_id . "'";

        // 接続したDBに対してSQL文を実行する
        $result = querySql($db, $sql);

        // refrigerator_contentsに登録済なら$row["cnt"]に1、未登録なら0が入る
        $row = mysqli_fetch_array($result);

        $goods_number = $row["content_number"] + $add_quantity;

        // 1個以上残るなら
        if ($goods_number != 0) {
            // データベースに更新
            $sql = "UPDATE refrigerator_contents SET "
            . "content_number = " . $goods_number . " "
            . "WHERE refrigerator_id = '" . $refrigerator_id . "' "
            . "AND goods_id = '" . $goods_id . "'";
        // 0個になるなら
        } else {
            $sql = "DELETE FROM refrigerator_contents "
              . "WHERE refrigerator_id ='" . $refrigerator_id . "' "
              . "AND goods_id = '" . $goods_id . "'";
        }

        // 接続したDBに対してSQL文を実行する
        $result = querySql($db, $sql);

        $status = "yes";
    }

    // メイン処理
    $arr["status"] = $status;

    // JSON_UNESCAPED_UNICODEでJSONを日本語で書く
    // JSON_PRETTY_PRINTでJSONを成形する
    print json_encode($arr, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
}

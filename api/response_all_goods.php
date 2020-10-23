<?php
/* =================
// 概要
// ================= */

// 選択したカテゴリーの中身全部出す！

/* =================
// プログラム
// ================= */

/* =================
// プログラム
// ================= */

// デフォルトステータスをdefaultに設定
$status = "default";

// query_sql.phpを呼び出す
require('not_api/query_sql.php');

// connect_db.phpを呼び出す（データベースに接続）
require('not_api/connect_db.php');

// category_idを受け取ったら
if (isset($_POST['category_id'])) {
    // category_idを受け取る
    $category_id = $_POST['category_id'];

    if ($category_id == "cat00") {
        // 全部のgoodsをすべて出す
        $sql = "SELECT * FROM goods";
    } else {
        // 該当カテゴリーのgoodsをすべて出す
        $sql = "SELECT * FROM goods WHERE category_id = '" . $category_id . "'";
    }
    // 接続したDBに対してSQL文を実行する
    $result = querySql($db, $sql);

    $array = array();
    // 全部出す
    foreach ($result as $row) {
        $goods_id = $row["goods_id"];
        $goods_name =$row["goods_name"];
        $goods_picture_name =$row["goods_picture_name"];
        // 配列の中に入れる
        // array_push($array, $array_text);
        array_push(
            $array,
            array(
            "goods_id" => $goods_id,
            "goods_name" => $goods_name,
            "goods_picture_name" => $goods_picture_name
          )
        );
    }
    // print_r($array);
    //
    $status = "yes";
} else {
    $status = "error";
}

// json作成
// 文字コード設定
header('Content-Type: application/json; charset=UTF-8');

// メイン処理
$arr["status"] = $status;
$arr["data"] = $array;

// JSON_UNESCAPED_UNICODEでJSONを日本語で書く
// JSON_PRETTY_PRINTでJSONを成形する
print json_encode($arr, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
// print json_encode($arr, JSON_UNESCAPED_UNICODE);

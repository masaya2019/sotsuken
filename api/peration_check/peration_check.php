<!DOCTYPE html>
<html lang="jp">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
</head>

<body>
  <h2>pre_registration.php</h2>
  <form action="../pre_registration.php" method="post">
    <p>メールアドレス</p>
    <input type="email" name="mail_address">
    <input type="submit" value="Go">
  </form>

  <br>

  <h2>token_check.php</h2>
  <form action="../token_check.php" method="post">
    <p>メールアドレス</p>
    <input type="email" name="mail_address">
    <p>認証コード（4桁）</p>
    <input type="text" name="token">
    <input type="submit" value="Go">
  </form>

  <h2>member_registration.php</h2>
  <form action="../member_registration.php" method="post">
    <p>メールアドレス</p>
    <input type="email" name="mail_address">
    <p>ユーザ名</p>
    <input type="text" name="user_name">
    <p>パスワード</p>
    <input type="text" name="password">
    <input type="submit" value="Go">
  </form>

  <h2>login_check.php</h2>
  <form action="../login_check.php" method="post">
    <p>メールアドレス</p>
    <input type="email" name="mail_address">
    <p>パスワード</p>
    <input type="text" name="password">
    <input type="submit" value="Go">
  </form>

  <h2>response_all_goods.php</h2>
  <form action="../response_all_goods.php" method="post">
    <p>キーワード、検索カテゴリID（cat00～cat07）</p>
    <input type="text" name="search_data">
    <p>冷蔵庫ID</p>
    <input type="text" name="refrigerator_id">
    <input type="submit" value="Go">
  </form>

  <h2>memo_check.php</h2>
  <form action="../memo_check.php" method="post">
    <p>冷蔵庫ID</p>
    <input type="text" name="refrigerator_id">
    <input type="submit" value="Go">
  </form>

  <h2>create_refrigerator_id.php</h2>
  <form action="../create_refrigerator_id.php" method="post">
    <p>メールアドレス</p>
    <input type="text" name="mail_address">
    <input type="submit" value="Go">
  </form>

  <h2>add_goods_quantity_database.php</h2>
  <form action="../add_goods_quantity_database.php" method="post">
    <p>冷蔵庫ID</p>
    <input type="text" name="refrigerator_id">
    <p>グッズID</p>
    <input type="text" name="goods_id">
    <p>追加量</p>
    <input type="text" name="add_quantity">
    <input type="submit" value="Go">
  </form>

  <h2>my_refrigerator_picture.php</h2>
  <form action="../my_refrigerator_picture.php" method="post">
    <p>冷蔵庫ID</p>
    <input type="text" name="refrigerator_id">
    <input type="submit" value="Go">
  </form>

  <h2>delete_my_refrigerator_picture.php</h2>
  <form action="../delete_my_refrigerator_picture.php" method="post">
    <p>冷蔵庫ID</p>
    <input type="text" name="refrigerator_id">
    <p>写真名</p>
    <input type="text" name="picture_name">
    <input type="submit" value="Go">
  </form>

  <br>
  <br>
  <br>
</body>

</html>
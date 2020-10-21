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
    <input type="email" name="mail_address" id="mail_address">
    <input type="submit" value="Go">
  </form>

  <br>

  <h2>token_check.php</h2>
  <form action="../token_check.php" method="post">
    <p>メールアドレス</p>
    <input type="email" name="mail_address" id="mail_address">
    <p>認証コード（4桁）</p>
    <input type="text" name="token" id="token">
    <input type="submit" value="Go">
  </form>

  <h2>member_registration.php</h2>
  <form action="../member_registration.php" method="post">
    <p>メールアドレス</p>
    <input type="email" name="mail_address" id="mail_address">
    <p>ユーザ名</p>
    <input type="text" name="user_name">
    <p>パスワード</p>
    <input type="text" name="password">
    <input type="submit" value="Go">
  </form>

  <h2>login_check.php</h2>
  <form action="../login_check.php" method="post">
    <p>メールアドレス</p>
    <input type="email" name="mail_address" id="mail_address">
    <p>パスワード</p>
    <input type="text" name="password">
    <input type="submit" value="Go">
  </form>

  <h2>response_all_goods.php</h2>
  <form action="../response_all_goods.php" method="post">
    <p>カテゴリID（cat01～cat07）</p>
    <input type="text" name="category_id" id="category_id">
    <input type="submit" value="Go">
  </form>

  <h2>memo_check.php</h2>
  <form action="../memo_check.php" method="post">
    <p>冷蔵庫ID（）</p>
    <input type="text" name="refrigerator_id" id="refrigerator_id">
    <input type="submit" value="Go">
  </form>

</body>

</html>
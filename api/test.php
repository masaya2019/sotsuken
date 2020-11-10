<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="utf-8">
    <title>testjson</title>
</head>
<body>
    <h1>テスト</h1>
    <form action="memo_add.php" method="POST">
    ID<input type="text" name="refrigerator_id">
    めあど<input type="text" name="mail_address">
    タイトル<input type="text" name="memo_title">
    内容<input type="text" name="memo_contents">
    <input type="submit" value="送信">
    </form>

    <h1>test</h1>
    <form action="upload_image.php" method="POST">
    <input type="file" name="image">
    <input type="submit" value="送信">
    </form>
</body>
</html>

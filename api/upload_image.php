<?php

$dir = "tmp_images/" . $_FILES['image']['name'];
move_uploaded_file($_FILES['image']['tmp_name'], $dir);

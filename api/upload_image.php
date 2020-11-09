<?php

$dir = "tmp_images/";
move_uploaded_file($_FILES['image']['tmp_name'], $dir);
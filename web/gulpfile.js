var gulp = require('gulp'),
    rename = require("gulp-rename"),
    concat = require("gulp-concat"),
    uglify = require('gulp-uglify'),
    watch = require("gulp-watch"),
    livereload = require('gulp-livereload');

gulp.task("default", ["build", "watch"]);

gulp.task("watch", function(){
    gulp.watch('./js/lib/*.js', ['build']);
});

gulp.task('build', function() {
    gulp.src('./js/lib/*.js')
        .pipe(concat('main.js'))
        .pipe(gulp.dest('./js/dist/'))
        .pipe(rename('main.min.js'))
        .pipe(uglify())
        .pipe(gulp.dest('./js/dist/'));
    gulp.src('./js/admin/*.js')
        .pipe(concat('admin.js'))
        .pipe(gulp.dest('./js/dist/'))
        .pipe(rename('admin.min.js'))
        .pipe(uglify())
        .pipe(gulp.dest('./js/dist/'));
});

var gulp = require('gulp'),
    rename = require('gulp-rename'),
    concat = require("gulp-concat"),
    uglify = require('gulp-uglify'),
    watch = require('gulp-watch'),
    livereload = require('gulp-livereload'),
    jshint = require('gulp-jshint'),
    stylish = require('jshint-stylish');

gulp.task("default", ["build", "watch"]);

gulp.task("watch", function(){
    gulp.watch('./js/lib/*.js', ['build']);
});

gulp.task('build', ['build_frontend', 'build_admin'])

gulp.task('build_frontend', function() {
    gulp.src('./js/lib/*.js')
        .pipe(jshint())
        .pipe(jshint.reporter('jshint-stylish'))
        .pipe(concat('main.js'))
        .pipe(gulp.dest('./js/dist/'))
        .pipe(rename('main.min.js'))
        .pipe(uglify())
        .pipe(gulp.dest('./js/dist/'));
});

gulp.task('build_admin', function(){
    gulp.src('./js/admin/*.js')
        .pipe(jshint())
        .pipe(jshint.reporter('jshint-stylish'))
        .pipe(concat('admin.js'))
        .pipe(gulp.dest('./js/dist/'))
        .pipe(rename('admin.min.js'))
        .pipe(uglify())
        .pipe(gulp.dest('./js/dist/'));
});

gulp.task('test', function(){
   gulp.src('./js/lib/*.js')
    .pipe(jshint())
    .pipe(jshint.reporter('jshint-stylish'))
    .pipe(jshint.reporter('fail'))
    .pipe(concat('main.js'))
    .pipe(gulp.dest('./js/dist/'))
    .pipe(rename("main.min.js"))
    .pipe(uglify())
    .pipe(gulp.dest('./js/dist/'));
});

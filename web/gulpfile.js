/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var gulp = require('gulp');
var rename = require("gulp-rename");
var concat = require("gulp-concat");
var uglify = require('gulp-uglify');
var watch = require("gulp-watch");
var livereload = require('gulp-livereload');
var jshint = require("gulp-jshint");
var stylish = require('jshint-stylish');

gulp.task("default", ["build", "watch"]);

gulp.task("watch", function(){
    gulp.watch('./js/lib/*.js', ['build'])
});

gulp.task('build', function() {
  gulp.src('./js/lib/*.js')
    .pipe(jshint())
    .pipe(jshint.reporter('jshint-stylish'))
    .pipe(concat('main.js'))
    .pipe(gulp.dest('./js/dist/'))
    .pipe(rename("main.min.js"))
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
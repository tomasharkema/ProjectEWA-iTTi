var gulp = require('gulp'),
    rename = require('gulp-rename'),
    concat = require("gulp-concat"),
    uglify = require('gulp-uglify'),
    watch = require('gulp-watch'),
    livereload = require('gulp-livereload'),
    jshint = require('gulp-jshint'),
    stylish = require('jshint-stylish'),
    less = require('gulp-less'),
    cssmin = require('gulp-cssmin'),
    gulpIf = require("gulp-if");
    
gulp.task("default", ["build", "watch"]);

gulp.task("watch", function(){
    gulp.watch(["./css/lib/*.css", "./css/less/**/*.less", './js/lib/*.js'], ['build']);
});

gulp.task('build', ['build_frontend', 'build_admin'])

var condition = function (file) {
    return !(file.path.indexOf("min") != -1 || file.path.indexOf("jquery") != -1);
};

gulp.task('build_frontend', ['build_frontend_js', 'build_frontend_css']);
gulp.task('build_frontend_js', function() {
    gulp.src(['./js/lib/dependencies/*.js', './js/lib/*.js'])
        .pipe(gulpIf(condition, jshint()))
        .pipe(jshint.reporter('jshint-stylish'))
        .pipe(concat('dryves.js'))
        .pipe(gulp.dest('./js/dist/'))
        .pipe(rename('dryves.min.js'))
        .pipe(uglify())
        .pipe(gulp.dest('./js/dist/'));
});

gulp.task('build_frontend_css', function(){
    gulp.src(["./css/lib/*.css", "./css/less/**/*.less"])
        .pipe(less())
        .pipe(concat('dryves.css'))
        .pipe(gulp.dest('./css/dist/'))
        .pipe(rename('dryves.min.css'))
        .pipe(cssmin())
        .pipe(gulp.dest('./css/dist/'))
    ;
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

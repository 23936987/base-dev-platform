var gulp = require('gulp');
var packageJSON  = require('./package');
var clean = require('gulp-clean');
var jshint = require('gulp-jshint');
var concat = require('gulp-concat');
var jshintConfig = packageJSON.jshintConfig;
var minifyCss = require('gulp-clean-css');
var uglify = require('gulp-uglify');
var sourceMap = require('gulp-sourcemaps');
var gutil = require('gulp-util');
var workPath = "./src";
var distPath =  "./lib/bdp/";


gulp.task('clean',function(){
    return gulp.src(distPath,{ read : false})       //src的第二个参数的{read:false}，是不读取文件,加快程序。
        .pipe(clean());
});

gulp.task('lint', function() {
    gulp.src('static/lib/cc/js/*.js')
        .pipe(jshint(jshintConfig))
        .pipe(jshint.reporter('default'));
});

gulp.task('imgCopy', function() {
    gulp.src(workPath +  '/**/*.{png,jpg,gif,ico,css}')
        .pipe(gulp.dest(distPath));

});

gulp.task('cssCopy', function() {
    gulp.src( workPath +'/css/*.css')
        //.pipe(concat('bdp.css'))
       // .pipe(minifyCss())
        .pipe(gulp.dest(distPath +'css'));
});
gulp.task('htmlCopy', function() {
    gulp.src(workPath +  '/html/*.html')
        .pipe(gulp.dest(distPath +'html'));
});

gulp.task('jsCopy', function() {
         gulp.src(workPath + '/js/*.js')
         .pipe(sourceMap.init())
        //.pipe(concat('cc.js'))
        //.pipe(uglify())
        /* .pipe(uglify({
                 mangle:true,
                 compress: true
             }
         ))
         .on('error', function (err) {
             gutil.log(gutil.colors.red('[Error]'), err.toString());
         })
         .pipe(sourceMap.write(distPath + 'maps'))*/
         .pipe(gulp.dest(distPath +'js'));
});

gulp.task('default', function() {
    gulp.start('imgCopy','cssCopy','jsCopy','htmlCopy');
});
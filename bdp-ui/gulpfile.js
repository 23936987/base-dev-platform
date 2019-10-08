var gulp = require('gulp');
var packageJSON  = require('./package');
var clean = require('gulp-clean');
var jshint = require('gulp-jshint');
var concat = require('gulp-concat');
var jshintConfig = packageJSON.jshintConfig;
var minifyCss = require('gulp-clean-css');
var uglify = require('gulp-uglify');


var workPath = "./src";
var distPath =  "./lib/bdp/";


var imagePath = [
    workPath +  '/**/*.{png,jpg,gif,ico,css}',
];
var cssPath=[
    workPath +'/css/Form.css',
    workPath +'/css/Text.css',
    workPath +'/css/TextArea.css',
    workPath +'/css/Number.css',
    workPath +'/css/Popup.css',
    workPath +'/css/Combo.css',
    workPath +'/css/ComboSearch.css',
    workPath +'/css/ComboTree.css',
    workPath +'/css/ComboBox.css',
    workPath +'/css/Layout.css',
    workPath +'/css/CheckBoxList.css',
    workPath +'/css/RadioBoxList.css',
    workPath +'/css/Form.css',
    workPath +'/css/FileUploadLogo.css',
	workPath +'/css/FileScreenshotLogo.css',
    workPath +'/css/ui.css',
    workPath + '/css/Money.css',
    workPath + '/css/DateTimePicker.css',
    workPath + '/css/ComboPager.css',
];

var htmlPath=[
    workPath +  '/html/*.html',
]
var jsPath=[
    workPath + '/js/bdp.js',
    workPath + '/js/JsonDtoModel.js',
    workPath + '/js/cc.js',
    workPath + '/js/Core.js',
    workPath + '/js/Component.js',
    workPath + '/js/Validate.js',
    workPath + '/js/Form.js',
    workPath + '/js/FormItem.js',
    workPath + '/js/Text.js',
    workPath + '/js/Hidden.js',
    workPath + '/js/TextArea.js',
    workPath + '/js/Number.js',
    workPath + '/js/LoadAjax.js',
    workPath + '/js/LoadPager.js',
    workPath + '/js/LoadListPager.js',
    workPath + '/js/Combo.js',
    workPath + '/js/ComboSearch.js',
    workPath + '/js/ComboBox.js',
    workPath + '/js/ComboDynamic.js',
    workPath + '/js/ComboData.js',
    workPath + '/js/ComboDict.js',
    workPath + '/js/ComboPager.js',
    workPath + '/js/ComboPagerData.js',
    workPath + '/js/ComboPagerDynamic.js',
    workPath + '/js/ComboTree.js',
    workPath + '/js/Pagination.js',
    workPath + '/js/PaginationData.js',
    workPath + '/js/PaginationDynamic.js',
    workPath + '/js/Popup.js',
    workPath + '/js/PopupIconCls.js',
    workPath + '/js/Tree.js',
    workPath + '/js/CheckBox.js',
    workPath + '/js/CheckBoxList.js',
    workPath + '/js/CheckBoxListDict.js',
    workPath + '/js/RadioBoxList.js',
    workPath + '/js/RadioBoxListDict.js',
    workPath + '/js/Editor.js',
    workPath + '/js/DateTimePicker.js',
    workPath + '/js/Layout.js',
    workPath + '/js/Button.js',
    workPath + '/js/ButtonAuth.js',
    workPath + '/js/EditButtonAuth.js',
    workPath + '/js/FileUploadLogo.js',
	workPath + '/js/FileScreenshotLogo.js',
    workPath + '/js/Details.js',
    workPath + '/js/Money.js',
    workPath + '/js/ModelSelect.js',
    workPath + '/js/AddPopup.js',
    workPath + '/js/AddModelSelect.js',
    workPath + '/js/ComboSyncTree.js',
    workPath + '/js/ButtomPopup.js',
    workPath + '/js/BottomModelSelect.js',
    workPath + '/js/ButtomTreePopup.js',

];
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
    gulp.src(imagePath)
        .pipe(gulp.dest(distPath));

});

gulp.task('cssCopy', function() {
    gulp.src(cssPath)
        .pipe(concat('bdp.css'))
        .pipe(minifyCss())
        .pipe(gulp.dest(distPath +'css'));
});
gulp.task('htmlCopy', function() {
    gulp.src(htmlPath)
        .pipe(gulp.dest(distPath +'html'));
});

gulp.task('jsCopy', function() {
         gulp.src(jsPath)
        //.pipe(concat('cc.js'))
        .pipe(uglify())
        .pipe(gulp.dest(distPath +'js'));
});

gulp.task('default', function() {
    gulp.start('imgCopy','cssCopy','jsCopy','htmlCopy');
});
var fs = require('fs');


const LANG_PATH = './src/assets/i18n/'
const files = fs.readdirSync(LANG_PATH);


for (let file of files) {
  const newPath = LANG_PATH + file;
  if (fs.lstatSync(newPath).isDirectory()) {
    const langFiles = fs.readdirSync(newPath);
    console.log(newPath);
    console.log(langFiles);
    console.log();
    console.log();
    let langMap = new Map();
    let lang = "{";

    for (let i = 0; i < langFiles.length; i++) {
      const langFile = langFiles[i];
      console.log('-------------------');
      console.log(i);

      const data = fs.readFileSync(newPath + '/' + langFile, 'utf8')
        .slice(0, fs.readFileSync(newPath + '/' + langFile, 'utf8').lastIndexOf('}'))
        .slice(fs.readFileSync(newPath + '/' + langFile, 'utf8').lastIndexOf('{') + 1)
        .split(',');

      for (let d of data) {
        d = (d.slice(d.lastIndexOf('\\r\\n  ') + 1));
        console.log(d);
        langMap.set(d.split(':')[0], d.split(':')[1])
      }


    }
    console.log(langMap);
    for (const d of langMap) {
      lang += d[0] + ':' + d[1] + ',';
    }

    console.log('lang', lang.substring(0, lang.length - 1))
    lang = lang.substring(0, lang.length - 1);
    lang += '}'
    fs.writeFileSync(newPath + ".json", lang)
    console.log(newPath + ".json created successfully")
  }
}

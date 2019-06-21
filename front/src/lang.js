var fs=require ('fs');

console.log('script');


const LANG_PATH='./src/assets/i18n/'
const files=fs.readdirSync(LANG_PATH);


console.log(typeof fs.readFileSync('./src/assets/i18n/'+files[3],'utf8'))


for(let file of files){
  const newPath=LANG_PATH+file
  if (fs.lstatSync(newPath).isDirectory()){
    const langFiles=fs.readdirSync(newPath);
    console.log(newPath)
    console.log(langFiles)
    console.log()
    console.log()

    let data="";
    for(let i=0;i<langFiles.length;i++){
      const langFile=langFiles[i];
      console.log('-------------------')
      console.log(i)
      /*console.log(newPath,langFile,fs.readFileSync(newPath+'/'+langFile, 'utf8'))
      console.log()
      console.log()
      console.log()*/
      if(i===0){
        data=data.concat(fs.readFileSync(newPath+'/'+langFile, 'utf8').slice(0,fs.readFileSync(newPath+'/'+langFile, 'utf8').lastIndexOf('}')),',')
        console.log('debut')
      }else if (i===langFiles.length-1){
        data=data.concat(fs.readFileSync(newPath+'/'+langFile, 'utf8').slice(fs.readFileSync(newPath+'/'+langFile, 'utf8').lastIndexOf('{')+1))
        console.log('fin')
      }else{
        data=data.concat(fs.readFileSync(newPath+'/'+langFile, 'utf8').slice(0,fs.readFileSync(newPath+'/'+langFile, 'utf8').lastIndexOf('}')).slice(1),',')
        console.log('mid')
        console.log(fs.readFileSync(newPath+'/'+langFile, 'utf8').slice(0,fs.readFileSync(newPath+'/'+langFile, 'utf8').lastIndexOf('}')).slice(1))
      }
    }
    console.log(data)
    fs.writeFileSync(newPath+".json",data)
  }
}

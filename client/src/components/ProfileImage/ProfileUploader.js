import AWS from 'aws-sdk';
const REGION = process.env.REACT_APP_REGION;
const ACESS_KEY_ID = process.env.REACT_APP_ACCESS_KEY_ID;
const SECRET_ACESS_KEY_ID = process.env.REACT_APP_SECRET_ACCESS_KEY_ID;
const S3_BUCKET = process.env.REACT_APP_S3_BUCKET;

//  s3로 파일을 올리는 함수
const uploadS3 = uploadSrc => {
  AWS.config.update({
    region: REGION,
    accessKeyId: ACESS_KEY_ID,
    secretAccessKey: SECRET_ACESS_KEY_ID,
  });

  const myBucket = new AWS.S3({
    params: { Bucket: S3_BUCKET },
    region: REGION,
  });

  const params = {
    ACL: 'public-read',
    Body: uploadSrc,
    Bucket: S3_BUCKET,
    Key: `profile/${uploadSrc.name}`,
  };
  //  유저프로필이라는 폴더안에, 유저가 업로드한 사진의 이름으로 올리겠다.

  myBucket
    .putObject(params)
    .on('httpUploadProgress', evt => {
      console.log(evt);
      // 여기서 돌려받은 파일의 s3url 을 리턴시켜야함.
    })
    .send(err => {
      if (err) console.log(err);
    });

  /* 
이렇게도 가능
const AWS = require('aws-sdk')
const s3 = new AWS.S3(config)

s3.upload(params).promise()
    .then((data)=>{
        console.log(data.Location)
    })
    .catch(err=>console.log(err))
    
    */
};

export default uploadS3;

import { S3Client, PutObjectCommand } from '@aws-sdk/client-s3';
import { v4 as uuidv4 } from 'uuid';

const ACESS_KEY_ID = process.env.REACT_APP_ACESS_KEY_ID;
const SECRET_ACESS_KEY_ID = process.env.REACT_APP_SECRET_ACESS_KEY_ID;
const S3_BUCKET = process.env.REACT_APP_S3_BUCKET;
const REGION = process.env.REACT_APP_REGION;

//  s3로 파일을 올리는 함수
async function uploadS3(uploadSrc) {
  const uniqeTitle = uuidv4();
  console.log(uniqeTitle);

  const params = {
    ACL: 'public-read',
    Body: uploadSrc,
    Bucket: S3_BUCKET,
    Key: `profile/${uniqeTitle}`,
  };

  const client = new S3Client({
    bucketName: S3_BUCKET,
    region: REGION,
    credentials: {
      accessKeyId: ACESS_KEY_ID,
      secretAccessKey: SECRET_ACESS_KEY_ID,
    },
  });

  try {
    await client.send(new PutObjectCommand(params));
  } catch (err) {
    console.log(err);
    return err;
  }
  return `https://${params.Bucket}.s3.ap-northeast-2.amazonaws.com/${params.Key}`;
}

export default uploadS3;

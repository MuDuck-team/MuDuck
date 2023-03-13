import { AiOutlineEye, AiOutlineHeart, AiTwotoneHeart } from 'react-icons/ai';
import { IoChatboxEllipsesOutline } from 'react-icons/io';

function ArticleCard({
  url,
  author,
  category,
  title,
  view,
  coments,
  hearts,
  hearted,
  time,
}) {
  return (
    <section>
      <div>
        <img alt="profile" src={url} />
        <div>
          {author} {time}
        </div>
      </div>
      <div>
        <div>
          [{category}] {title}
        </div>
        <div>
          {category === '공지사항' ? (
            <>
              <AiOutlineEye /> {view}
            </>
          ) : (
            <>
              <div>
                <AiOutlineEye /> {view}
              </div>
              <div>
                <IoChatboxEllipsesOutline /> {coments}
              </div>
              <div>
                {hearted === true ? <AiTwotoneHeart /> : <AiOutlineHeart />}{' '}
                {hearts}
              </div>
            </>
          )}
        </div>
      </div>
    </section>
  );
}

export default ArticleCard;

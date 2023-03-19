import { atom } from 'recoil';
import { recoilPersist } from 'recoil-persist';

const { persistAtom } = recoilPersist({
  key: 'recoil-persist',
  storage: localStorage,
});

const userInfo = atom({
  key: 'userInfo',
  default: {
    id: null,
    token: '',
    nickname: '',
    profileImageUrl: '',
    role: '',
  },
  effects_UNSTABLE: [persistAtom],
});

export default userInfo;

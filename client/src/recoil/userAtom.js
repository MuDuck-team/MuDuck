import { atom } from 'recoil';
import { recoilPersist } from 'recoil-persist';

const { persistAtom } = recoilPersist({
  key: 'recoil-persist',
  storage: localStorage,
});

export const userInfo = atom({
  key: 'userInfo',
  default: {
    id: null,
    nickname: '',
    profileImageUrl: '',
    role: '',
  },
  effects_UNSTABLE: [persistAtom],
});

export const userState = atom({
  key: 'userState',
  default: false,
});

export const adminState = atom({
  key: 'adinState',
  default: false,
});

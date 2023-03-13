import { atom } from 'recoil';
import { recoilPersist } from 'recoil-persist';

const { persistAtom } = recoilPersist({
  key: 'recoil-persist',
  storage: localStorage,
});

const userInfo = atom({
  key: 'UserInfo',
  default: null,
  effects_UNSTABLE: [persistAtom],
});

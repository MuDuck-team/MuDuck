import { atom } from 'recoil';
import { recoilPersist } from 'recoil-persist';

const { persistAtom } = recoilPersist({
  key: 'recoil-persist',
  storage: localStorage,
});

const userInfo = atom({
  key: 'userInfo',
  default: null,
  effects_UNSTABLE: [persistAtom],
});

export default userInfo;

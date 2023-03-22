export default {
  boardContent: {
    head: {
      userProfile: 'https://cataas.com/cat/pbrosoqOlUUtR5XJ',
      nickname: '뮤지컬덕후',
      createdAt: '2023.01.02',
      view: 283,
      totalComment: 6,
      category: '자유주제',
    },
    body: {
      title: '뮤덕들이 좋아하는 뮤지컬 넘버는 뭐야?',
      content:
        '난 1개만 꼽으라고 하면 못꼽겠고 탑4 꼽으면 오유 레미즈 노트르담 해밀턴',
    },
    liked: true,
  },
  comments: [
    {
      id: 1,
      head: {
        userProfile: 'https://cataas.com/cat/mHUk8cIC4WAHXoIE',
        nickname: '저기멘델리로',
        createdAt: '2023.01.02 21:22',
      },
      body: '레미 지킬 노틀담 해밀턴 전넘버 무한 재생으로 돌려도 스킵 안하는 극 ㅋㅋㅋㅋ',
      parentId: null,
      comments: [
        {
          id: 2,
          head: {
            userProfile: 'https://cataas.com/cat/pbrosoqOlUUtR5XJ',
            nickname: '뮤지컬광기',
            createdAt: '2023.01.02 21:22',
          },
          body: '저도ㅋㅋㅋㅋㅋ 하루종일 무한재생가능',
          parentId: 1,
          comments: [],
        },
        {
          id: 3,
          head: {
            userProfile: 'https://cataas.com/cat/WfpbMKHNwu29kqGW',
            nickname: '주말마다뮤지컬',
            createdAt: '2023.01.02 21:48',
          },
          body: '진짜 스킵할게 없어요 ㅠ',
          parentId: 1,
          comments: [],
        },
      ],
    },
    {
      id: 4,
      head: {
        userProfile: 'https://cataas.com/cat/mHUk8cIC4WAHXoIE',
        nickname: '뮤덕뮤덕',
        createdAt: '2023.01.02 21:22',
      },
      body: '너무 많아... 고를 수 없어...',
      parentId: null,
      comments: [
        {
          id: 5,
          head: {
            userProfile: 'https://cataas.com/cat/pbrosoqOlUUtR5XJ',
            nickname: '뮤지컬광기',
            createdAt: '2023.01.02 21:23',
          },
          body: '최애 넘버는 뭔데?',
          parentId: 4,
          comments: [],
        },
      ],
    },
    {
      id: 6,
      head: {
        userProfile: 'https://cataas.com/cat/mHUk8cIC4WAHXoIE',
        nickname: '뮤덕임다',
        createdAt: '2023.01.02 21:24',
      },
      body: '헐 나랑 취향 똑같아 대박',
      parentId: null,
      comments: null,
    },
  ],
};

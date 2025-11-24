import http from 'k6/http';
import { check, sleep } from 'k6';
import { randomItem } from 'https://jslib.k6.io/k6-utils/1.2.0/index.js';

// 1. 부하 테스트 설정 (옵션)
export const options = {
  // 가상 사용자(VU) 10명이 30초 동안 계속 요청을 보냄
  vus: 10,
  duration: '10s',

  // (선택사항) 점진적 부하 증가 시나리오가 필요하면 아래 주석 해제
  /*
  stages: [
    { duration: '10s', target: 10 }, // 처음 10초 동안 VU 10명까지 증가
    { duration: '20s', target: 10 }, // 20초 동안 유지
    { duration: '10s', target: 0 },  // 10초 동안 종료
  ],
  */
};

// 2. 보낼 메시지 목록 정의 (로그에 있는 패턴)
const messages = [
  "수신 받은 데이터1",
  "수신 받은 데이터2",
  "수신 받은 데이터3",
  "수신 받은 데이터4",
  "수신 받은 데이터5",
  "수신 받은 데이터6"
];

export default function () {
  // 요청할 URL (로그상 대부분 8080이므로 8080으로 설정)
  const url = 'http://localhost:8081/callback';

  // 헤더 설정
  const params = {
    headers: {
      'Content-Type': 'application/json',
    },
  };

  // 3. 페이로드 생성
  // 메시지 목록 중 하나를 랜덤으로 선택하여 전송
  const payload = JSON.stringify({
    txId: 1,  // 로그에 있는 그대로 1로 고정 (동적 변경 필요 시 아래 팁 참고)
    message: randomItem(messages),
  });

  // 4. POST 요청 전송
  const res = http.post(url, payload, params);

  // 5. 검증 (응답 코드가 200인지 확인)
  check(res, {
    'status is 200': (r) => r.status === 200,
  });

  // 요청 간격 조절 (예: 0.5초 ~ 1초 사이 랜덤 대기)
  // 너무 빠르게 보내면 서버가 바로 죽을 수 있으므로 적절히 조절하세요.
  // sleep(Math.random() * 0.5 + 0.5);
}
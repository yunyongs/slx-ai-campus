package lecture.exercise;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Application {

        public static void main(String[] args) {

            List<Movie> movies = Arrays.asList(
                    new Movie("레디 플레이어 원", "SF", 2018, 8.3),
                    new Movie("어벤져스: 엔드게임", "액션", 2019, 8.7),
                    new Movie("소울", "애니메이션", 2020, 8.7),
                    new Movie("듄", "SF", 2021, 8.4),
                    new Movie("탑건: 매버릭", "액션", 2022, 8.8),
                    new Movie("오펜하이머", "드라마", 2023, 8.7),
                    new Movie("듄: 파트 2", "SF", 2024, 9.0),
                    new Movie("미키 17", "SF", 2025, 7.7)
            );

            // 장르와 평점은 스트림 연습을 위해 구성한 실습용 데이터이다.

            // 1. 장르가 "SF"인 영화의 평균 평점을 구하고 소수점 둘째 자리까지 출력하기
            // 결과 : 1. SF 영화의 평균 평점: 8.35
            double sfAverageRating = movies.stream()
                    .filter(movie -> movie.getGenre().equals("SF"))
                    .mapToDouble(Movie::getRating)
                    .average()
                    .getAsDouble();
            System.out.println("sfAverageRating = " + sfAverageRating);

            // 2. 2021년부터 개봉한 영화를 평점 내림차순으로 정렬하여 제목 리스트로 출력하기
            // 결과 : 2. 2021년부터 개봉한 영화: [듄: 파트 2, 탑건: 매버릭, 오펜하이머, 듄, 미키 17]
            List<String> titleFrom2021 = movies.stream()
                    .filter(movie -> movie.getReleaseYear() >= 2021)
                    //.sorted(Comparator.comparingDouble(Movie::getRating).reversed())
                    .sorted(
                            (movie1, movie2) 
                                    -> Double.compare(movie2.getRating(), movie1.getRating()))
                    .map(Movie::getTitle)
                    .toList();
            System.out.println("titleFrom2021 = " + titleFrom2021);
            
            /*
            * Wrapping compare() : comparator 사용 가능한 값을 비교하는 메서드
            * */

            // 3. 개봉 연도를 기준으로 가장 최근에 개봉한 영화 3편의 제목을 출력하기
            // 결과 : 3. 최근 개봉 영화 3편: [미키 17, 듄: 파트 2, 오펜하이머]
            List<String> recent3Movie = movies.stream()
                    .sorted(
                            (movie1, movie2)
                                    -> Integer.compare(movie2.getReleaseYear(), movie1.getReleaseYear())
                    )
                    .limit(3)
                    .map(movie -> movie.getTitle()).toList();
            System.out.println("recent3Movie = " + recent3Movie);

            // 4. 평점이 8.7 이상인 영화들의 장르를 중복 없이 오름차순으로 정렬하여 출력하기
            // 결과 : 4. 평점 8.7 이상 영화의 장르: [SF, 드라마, 애니메이션, 액션]
            List<String> highRatingMovies = movies.stream()
                    .filter(movie -> movie.getRating() >= 8.7)
                    .map(Movie::getGenre)
                    .distinct()
                    .sorted()
                    .toList();

            System.out.println("highRatingMovies = " + highRatingMovies);
        }
    }


package study.yim0327.spring_chat.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import study.yim0327.spring_chat.util.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /** 잘못된 파라미터 (서비스/도메인) */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<?>> handleIllegalArgument(IllegalArgumentException e) {
        return ResponseEntity
                .badRequest() // 400 BAD REQUEST
                .body(ApiResponse.failure(e.getMessage()));
    }

    /**  DTO @Valid 검증 실패 */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidation(MethodArgumentNotValidException e) {
        String message = e.getBindingResult()
                .getFieldErrors()   // 발생한 모든 오류들의 리스트
                .get(0)             // 첫 번째 오류만 사용
                .getDefaultMessage();

        return ResponseEntity
                .badRequest() // 400 BAD REQUEST
                .body(ApiResponse.failure(message));
    }

    /** 그 외 예상 못한 모든 에러 */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR) // 500 INTERNAL_SERVER_ERROR
                .body(ApiResponse.failure("서버 오류가 발생했습니다."));
    }

}

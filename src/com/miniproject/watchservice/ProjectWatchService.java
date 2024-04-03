package com.miniproject.watchservice;

import com.miniproject.env.EnvProperties;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.WatchEvent.Kind;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ProjectWatchService implements Runnable {
    private String watchPath;
    WatchService service = null;

    public ProjectWatchService(String watchPath) {
        this.watchPath = watchPath;

        try {
            service = FileSystems.getDefault().newWatchService();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            //path 객체를 얻는다
            Path path = Paths.get(watchPath);

            //path에 watch service를 등록한다
            //등록시 감시할 항목을 설정한다
            path.register(service,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_MODIFY);
            
            while(true) {
                //서비스로 부터 감시 키를 얻는다
                WatchKey key = service.take();

                //경로에 설정한 감시 항목 중 변경 이벤트가 발생하면 이벤트 목록을 얻는다
                dispatchPollEvents(key.pollEvents());

                //키 리셋
                if(!key.reset()) break;
            }
            service.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void dispatchPollEvents(List<WatchEvent<?>> list) {
        for(WatchEvent<?> event : list) {
            dispatchWatchEvent(event);
        }
    }

    private void dispatchWatchEvent(WatchEvent<?> event) {
        Kind<?> kind = event.kind();
        Path path = (Path) event.context();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss", Locale.KOREA);

        try {
            if (kind.equals(StandardWatchEventKinds.ENTRY_CREATE)) {
                //파일이 생성되었을 때 실행되는 코드
                EnvProperties.loadProperties();
                System.out.println("파일생성 : " + path.getFileName());
                System.out.println("프로그램을 재시작해주세요.");
            } else if (kind.equals(StandardWatchEventKinds.ENTRY_DELETE)) {
                //파일이 삭제되었을 때 실행되는 코드
                EnvProperties.loadProperties();
                System.out.println("파일삭제 : " + path.getFileName());
                System.out.println("프로그램을 재시작해주세요.");
            } else if (kind.equals(StandardWatchEventKinds.ENTRY_MODIFY)) {
                //파일이 수정되었을 때 실행되는 코드
                EnvProperties.loadProperties();
                System.out.println("-------------------");
                System.out.println("파일이 수정되었습니다. " + format.format(new Date()));
//                System.out.println("수정 : " + path.getFileName());
            } else if (kind.equals(StandardWatchEventKinds.OVERFLOW)) {
                //운영체제에서 이벤트가 소실되었거나 버려질 경우 실행되는 코드
                EnvProperties.loadProperties();
                System.out.println("OVERFLOW");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

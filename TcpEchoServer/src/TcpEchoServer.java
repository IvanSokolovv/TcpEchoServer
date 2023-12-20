import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpEchoServer {

    public static void main(String[] args) throws IOException {
        while (true) {
            final int PORT = Integer.parseInt(args[0]);
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Ждем подключения клиента");
            Socket acceptedSocket = serverSocket.accept();
            System.out.println("Клиент подключен, принимаем данные");

            InputStream inputStream = acceptedSocket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);

            String acceptedInfo = dataInputStream.readUTF();
            System.out.println("Принято: " + acceptedInfo);

            OutputStream outputStream = acceptedSocket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            String toSend = "Отправляем: " + acceptedInfo;
            dataOutputStream.writeUTF(toSend);
            System.out.println("Отправлено сервером: " + toSend);

            inputStream.close();
            outputStream.close();
            serverSocket.close();
            System.out.println("Сервер закончил свою работу");
        }
    }
}
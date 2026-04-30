import socket

HOST = '0.0.0.0'
PORT = 5000

server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server_socket.bind((HOST, PORT))
server_socket.listen(1)

conn, addr = server_socket.accept()

try:
    while True:
        data = conn.recv(1024)
        if not data:
            break

        message = data.decode('utf-8')
        print(message)

        if message.lower() == "exit":
            break

        response = input()
        conn.sendall(response.encode('utf-8'))

        if response.lower() == "exit":
            break
finally:
    conn.close()
    server_socket.close()
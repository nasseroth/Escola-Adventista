import { API_CONFIG, API_CONFIG_T } from './../../config/api.config';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../dto/user-dto';
import { take } from 'rxjs/operators';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {

  }

  login(user: User) {
    return this.http.post(`${API_CONFIG_T}/api/auth`, user).pipe(take(1));
  }
/*
  put(usuario: User): Observable<any> {
    return this.http.put(`${API_CONFIG.baseUrl}/api/user/`, usuario).pipe(take(1));
  }
*/
  post(usuario: User): Observable<any> {
    return this.http.post(`${API_CONFIG.baseUrl}/api/user/`, usuario).pipe(take(1));
  }
  findAll(): Observable<Array<User>> {
    return this.http.get<Array<User>>(`${API_CONFIG.baseUrl}/api/user/mostrar-tudo`).pipe(take(1));
  }


  createOrUpdate(user: User) {
    if (user.id !== null && user.id !== '') {
      return this.http.put(`${API_CONFIG}/api/user`, user);
    } else {
      user.id = null;
      return this.http.post(`${API_CONFIG}/api/user`, user);
    }
  }
/*
  findById(id: string) {
    return this.http.get(`${API_CONFIG_T}/api/user/${id}`);
  }
*/
  findById(id: number): Observable<User> {
    return this.http.get<User>(`${API_CONFIG.baseUrl}/api/user/${id}`).pipe(take(1));
  }

  delete(id: number) {
    return this.http.delete(`${API_CONFIG_T}/api/user/${id}`);
  }

  totalUsuarios(): Observable<any> {
    return this.http.get<any>(`${API_CONFIG.baseUrl}/api/user/total-usuarios`).pipe(take(1));
  }
}

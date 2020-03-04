import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GeoService {

  constructor(private http: HttpClient) { }

  getEstados(): Observable<any> {
    return this.http.get('http://www.geonames.org/childrenJSON?geonameId=3469034');
  }

  getCidades(idEstado): Observable<any> {
    return this.http.get(`http://www.geonames.org/childrenJSON?geonameId=${idEstado}`);
  }

}

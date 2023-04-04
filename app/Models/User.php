<?php

namespace App\Models;

use Illuminate\Foundation\Auth\User as Authenticatable;
use Laravel\Sanctum\HasApiTokens;

class User extends Authenticatable
{
    use HasApiTokens;

    public $timestamps = false;

    /**
     * @var array<int, string>
     */
    protected $fillable = [
        'login',
        'password',
        'uuid',
    ];

    /**
     * @var array<int, string>
     */
    protected $hidden = [
        'password',
    ];
}

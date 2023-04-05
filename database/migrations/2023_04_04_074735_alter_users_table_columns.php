<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    public function up(): void
    {
        Schema::table('users', function (Blueprint $table) {
            $table->renameColumn('email', 'login');
            $table->uuid('uuid')->unique();
            $table->dropColumn(['name', 'email_verified_at']);
            $table->dropRememberToken();
            $table->dropTimestamps();
        });
    }

    public function down(): void
    {
        Schema::table('users', function (Blueprint $table) {
            $table->timestamps();
            $table->rememberToken();
            $table->string('name');
            $table->timestamp('email_verified_at')->nullable();
            $table->dropColumn('uuid');
            $table->renameColumn('login', 'email');
        });
    }
};
